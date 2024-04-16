package emu.grasscutter.server.packet.recv;

import emu.grasscutter.game.entity.EntityBaseGadget;
import emu.grasscutter.game.quest.enums.QuestContent;
import emu.grasscutter.net.packet.*;
import emu.grasscutter.net.proto.GadgetInteractReqOuterClass.GadgetInteractReq;
import emu.grasscutter.net.proto.InterOpTypeOuterClass;
import emu.grasscutter.net.proto.InteractTypeOuterClass;
import emu.grasscutter.server.game.GameSession;
import emu.grasscutter.server.packet.send.PacketGadgetInteractRsp;

@Opcodes(PacketOpcodes.GadgetInteractReq)
public class HandlerGadgetInteractReq extends PacketHandler {

    @Override
    public void handle(GameSession session, byte[] header, byte[] payload) throws Exception {
        GadgetInteractReq req = GadgetInteractReq.parseFrom(payload);

        session
                .getPlayer()
                .getQuestManager()
                .queueEvent(QuestContent.QUEST_CONTENT_INTERACT_GADGET, req.getGadgetId());
        session.getPlayer().interactWith(req.getGadgetEntityId(), req);

//        session.getPlayer().sendPacket(
//            new PacketGadgetInteractRsp(
//                req.getGadgetId(),
//                req.getGadgetEntityId(),
//                InteractTypeOuterClass.InteractType.INTERACT_TYPE_NONE,
//                InterOpTypeOuterClass.InterOpType.INTER_OP_TYPE_FINISH));
    }
}
